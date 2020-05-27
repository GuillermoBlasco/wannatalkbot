package org.wannatalk.telegrambot.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wannatalk.telegrambot.data.entities.Link;
import org.wannatalk.telegrambot.data.entities.LinkRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ScrapeWebService scrapeWebService;
    private final LinkRepository linkRepository;

    public void newLink(String url) {
        Optional<Link> existingLink = linkRepository.findByUrl(url);
        Link link;
        if (existingLink.isEmpty()) {
            link = new Link();
        } else {
            link = existingLink.get();
        }
        link.setUrl(url);
        link.setTimestamp(LocalDateTime.now());
        ScrapeData scrapeData = scrapeWebService.parseContent(url);
        link.setTitle(scrapeData.getTitle());
        link.setDescription(scrapeData.getDescription());
        link.setTextSearch(link.getTitle() + " " + link.getDescription());
        linkRepository.save(link);
    }

    public List<Link> findLink(String text) {
        return linkRepository.findAllByTextSearchLike(text);
    }


}
