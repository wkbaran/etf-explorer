package etf.explorer

class BasicController {

    def etfDataService

    def index() {
        def ticker = 'SPY'
        def results = etfDataService.scrapeEtfComponents(ticker)
        [ticker:ticker, elements:results]
    }
}
