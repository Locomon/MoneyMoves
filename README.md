# MoneyMoves

MoneyMoves is a financial analytic program and UI overlay that I code in my spare time.

Essentially, it provides a master list of companies, organized by sector and industry, and will provide a rapidly-sourced endpoint that lists the fundamentals of the stock, as well as a full timeseries over the last five years. This timeseries will also include on-the-fly calculations of the function's RSI and moving averages, as well as its moving averages. 

Further refinements of this program are underway. Among other things I intend to do, include:
- Fully 'akka'-fying the Java backend, and converting the assorted loaders into using an Akka layer.
- Adding a Python middleware layer called from the UI, that can be used for executing rest calls on the main Java backend as well as the other backend layers. This would allow for more dynamic data aggregation.
- Adding task-schedulers for semi-periodic updates/moving average data.
- Updating the main ticker-list to have a "quoted" list.
- Externalizing the url configs for the front-end, so that the backend can be deployed on a private server
- Externalizing the data storage into an external fileshare (e.x. a personal NAS device)
- Basic option pricing, to derive the 'fair' value of an option price (that is, what should its price be, assuming implied volatility = actual volatility), in order to assist in harvesting IV-crush.

(Dream: Deployment of the application onto an externalized cloud container (e.x. AWS, or Heroku), and then being able to remote into there from anywhere).