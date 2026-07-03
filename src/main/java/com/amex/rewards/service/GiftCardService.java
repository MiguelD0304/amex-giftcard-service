package com.amex.rewards.service;

import com.amex.rewards.model.GiftCard;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GiftCardService {

    private final Map<String, GiftCard> storage = new ConcurrentHashMap<>();

    //POST /gift_cards
    public GiftCard createGiftCard(GiftCard card){
        String uuid = UUID.randomUUID().toString();
        card.setId(uuid);
        storage.put(uuid, card);
        return card;
    }

    //GET /gift_cards/[id]
    public Optional<GiftCard> getGiftCardById(String id){
        return Optional.ofNullable(storage.get(id));
    }

    //DELETE /gift_cards/[id]
    public boolean deleteGiftCard(String id){
        if (storage.containsKey(id)){
            storage.remove(id);
            return true;
        }
        return false;
    }

    // GET /gift_cards supported query parameters for filtering by value and company_name
    public List<GiftCard> getAllGiftCards(String value, String companyName){
        List<GiftCard> result = new ArrayList<>();

        for (GiftCard card: storage.values()){
            // If a specific value is provided, skip cards that don't match.
            if (value != null && !value.equalsIgnoreCase(card.getValue())) {
                continue;
            }

            // If a company name filter is provided, skip cards that don't match.
            if (companyName != null && !companyName.equalsIgnoreCase(card.getCompanyName())) {
                continue;
            }

            // Collect the element only if it passes all filtering.
            result.add(card);
        }
        return result;
    }
}
