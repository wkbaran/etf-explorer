package etf.explorer

import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.HttpRequest

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import org.apache.commons.lang3.StringUtils

import org.springframework.beans.factory.annotation.Value

class EtfDataService {

    @Value('${etfData.endpoint}')
    String endpoint

    List<EtfHoldingDO> scrapeEtfComponents ( String tickerSymbol ) {
        BlockingHttpClient client = HttpClient.create(endpoint.toURL()).toBlocking()

        HttpRequest request = HttpRequest
                .GET("/etf/${tickerSymbol}/")
        def resp = client.retrieve(request)
        client.close()

        List<EtfHoldingDO> results = []

        Document document = Jsoup.parse(resp)
        def rows = document.select("#etf-holdings > tbody > tr")
        rows.each {
            def tds = it.select("td")
            results << new EtfHoldingDO(
                    symbol: tds.get(0).select("a").text(),
                    name: tds.get(1).text(),
                    // remove the last character from the amount percentage
                    amountPercentage: new BigDecimal(StringUtils.chop(tds.get(2).text()))
            )
        }

        results
    }

}
