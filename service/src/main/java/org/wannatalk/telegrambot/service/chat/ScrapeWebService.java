package org.wannatalk.telegrambot.service.chat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ScrapeWebService {

    public ScrapeData parseContent(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            doc.title();
            ScrapeData scrapeData = new ScrapeData();
            scrapeData.setTitle(doc.title());
            scrapeData.setDescription(getDescription(doc).orElse(null));
            return scrapeData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Optional<String> getDescription(Document doc) {
        return doc.head().children().stream().filter(x -> x.attr("name").equals("description")).map(x -> x.attr("content")).findAny();
    }
}
