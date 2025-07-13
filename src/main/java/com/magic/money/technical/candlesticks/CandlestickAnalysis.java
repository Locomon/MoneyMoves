package com.magic.money.technical.candlesticks;

import com.magic.money.core.domain.InstrumentTimeseriesDatapoint;

public class CandlestickAnalysis {
	public static CANDLESTICK getCandlestick(InstrumentTimeseriesDatapoint dp) {
	    double body = Math.abs(dp.getClose() - dp.getOpen());
	    double upperShadow = dp.getHigh() - Math.max(dp.getOpen(), dp.getClose());
	    double lowerShadow = Math.min(dp.getOpen(), dp.getClose()) - dp.getLow();
	    double totalRange = dp.getHigh() - dp.getLow();
	
	    boolean isDoji = body < totalRange * 0.1;
	
	    if (isDoji) {
	        if (upperShadow > totalRange * 0.4 && lowerShadow > totalRange * 0.4) return CANDLESTICK.LONG_LEGGED_DOJI;
	        if (upperShadow > lowerShadow) return CANDLESTICK.GRAVESTONE_DOJI;
	        if (lowerShadow > upperShadow) return CANDLESTICK.DRAGONFLY_DOJI;
	        return CANDLESTICK.DOJI;
	    }
	
	    if (dp.getOpen() < dp.getClose()) { // Bullish
	        if (lowerShadow > body * 2 && upperShadow < body) return CANDLESTICK.HAMMER;
	        if (upperShadow > body * 2 && lowerShadow < body) return CANDLESTICK.INVERTED_HAMMER;
	        if (upperShadow < 0.01 && lowerShadow < 0.01) return CANDLESTICK.WHITE_MARUBOZU;
	        return CANDLESTICK.BULLISH_SPINNING_TOP;
	    } else { // Bearish
	        if (upperShadow > body * 2 && lowerShadow < body) return CANDLESTICK.SHOOTING_STAR;
	        if (lowerShadow > body * 2 && upperShadow < body) return CANDLESTICK.HANGING_MAN;
	        if (upperShadow < 0.01 && lowerShadow < 0.01) return CANDLESTICK.BLACK_MARUBOZU;
	        return CANDLESTICK.GRAVESTONE_DOJI; // fallback to gravestone when in doubt
	    }
	}
	
	public static CANDLESTICK_PAIR getCandlestickPair(InstrumentTimeseriesDatapoint one, InstrumentTimeseriesDatapoint two) {
	    boolean firstBull = one.getClose() > one.getOpen();
	    boolean secondBull = two.getClose() > two.getOpen();
	
	    if (!firstBull && secondBull) {
	        if (two.getOpen() < one.getClose() && two.getClose() > one.getOpen()) return CANDLESTICK_PAIR.BULLISH_ENGULFING;
	        if (two.getOpen() < one.getClose() && two.getClose() > (one.getOpen() + one.getClose()) / 2) return CANDLESTICK_PAIR.PIERCING_LINE;
	        if (one.getLow() == two.getLow()) return CANDLESTICK_PAIR.TWEEZER_BOTTOMS;
	        if (one.getOpen() > two.getOpen() && one.getClose() > two.getClose() && two.getOpen() > two.getClose() && 
	            two.getOpen() < one.getClose() && two.getClose() > one.getOpen())
	            return CANDLESTICK_PAIR.BULLISH_HARAMI;
	    }
	
	    if (firstBull && !secondBull) {
	        if (two.getOpen() > one.getClose() && two.getClose() < one.getOpen()) return CANDLESTICK_PAIR.BEARISH_ENGULFING;
	        if (two.getOpen() > one.getClose() && two.getClose() < (one.getOpen() + one.getClose()) / 2) return CANDLESTICK_PAIR.DARK_CLOUD_COVER;
	        if (one.getHigh() == two.getHigh()) return CANDLESTICK_PAIR.TWEEZER_TOPS;
	        if (one.getOpen() < two.getOpen() && one.getClose() < two.getClose() && two.getOpen() < two.getClose() && 
	            two.getOpen() > one.getClose() && two.getClose() < one.getOpen())
	            return CANDLESTICK_PAIR.BEARISH_HARAMI;
	    }
	
	    return null;
	}
	
	public static CANDLESTICK_TRIO getCandlestickTrio(InstrumentTimeseriesDatapoint one, InstrumentTimeseriesDatapoint two, InstrumentTimeseriesDatapoint three) {
	    boolean firstBull = one.getClose() > one.getOpen();
	    boolean thirdBull = three.getClose() > three.getOpen();
	
	    if (!firstBull && three.getClose() > one.getOpen() && two.getClose() > two.getOpen()) {
	        if (two.getClose() < one.getClose() && two.getOpen() > three.getOpen()) return CANDLESTICK_TRIO.MORNING_STAR;
	        if (one.getClose() < two.getOpen() && three.getClose() > two.getClose() && three.getOpen() > two.getOpen())
	            return CANDLESTICK_TRIO.THREE_WHITE_SOLDIERS;
	        if (one.getClose() > two.getOpen() && three.getClose() > one.getOpen())
	            return CANDLESTICK_TRIO.THREE_INSIDE_UP;
	    }
	
	    if (firstBull && three.getClose() < one.getOpen() && two.getClose() < two.getOpen()) {
	        if (two.getClose() > one.getClose() && two.getOpen() < three.getOpen()) return CANDLESTICK_TRIO.EVENING_STAR;
	        if (one.getClose() > two.getOpen() && three.getClose() < two.getClose() && three.getOpen() < two.getOpen())
	            return CANDLESTICK_TRIO.THREE_BLACK_CROWS;
	        if (one.getClose() < two.getOpen() && three.getClose() < one.getOpen())
	            return CANDLESTICK_TRIO.THREE_INSIDE_DOWN;
	    }
	
	    if (!firstBull && two.getOpen() > one.getClose() && two.getClose() < two.getOpen() && 
	        three.getOpen() > two.getClose() && three.getClose() > three.getOpen()) {
	        return CANDLESTICK_TRIO.BULLISH_ABANDONED_BABY;
	    }
	
	    if (firstBull && two.getOpen() < one.getClose() && two.getClose() > two.getOpen() && 
	        three.getOpen() < two.getClose() && three.getClose() < three.getOpen()) {
	        return CANDLESTICK_TRIO.BEARISH_ABANDONED_BABY;
	    }
	
	    return null;
	}
}
