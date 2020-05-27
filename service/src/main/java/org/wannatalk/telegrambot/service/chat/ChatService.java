package org.wannatalk.telegrambot.service.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wannatalk.telegrambot.data.entities.Link;
import org.wannatalk.telegrambot.data.entities.LinkRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ScrapeWebService scrapeWebService;
    private final LinkRepository linkRepository;

    public void newLink(String url, String userId) {
        Optional<Link> existingLink = linkRepository.findByUrlAndUserId(url, userId);
        Link link;
        if (existingLink.isEmpty()) {
            link = new Link();
        } else {
            link = existingLink.get();
        }
        link.setUrl(url);
        link.setTimestamp(LocalDateTime.now());
        link.setUserId(userId);
        ScrapeData scrapeData = scrapeWebService.parseContent(url);
        link.setTitle(scrapeData.getTitle());
        link.setDescription(scrapeData.getDescription());
        link.setTextSearch(link.getTitle() + " " + link.getDescription());
        linkRepository.save(link);
        log.info("New link stored");
    }

    public List<Link> findLink(String text, String userId) {
        return linkRepository.findAllByTextSearchLikeAndUserId(text, userId);
    }


}
