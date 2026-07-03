package com.amex.rewards;

import com.amex.rewards.model.GiftCard;
import com.amex.rewards.service.GiftCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GiftCardServiceTest {

    private GiftCardService giftCardService;

    //Initialise the card data
    @BeforeEach
    public void setUp() {
        giftCardService = new GiftCardService();

        // Create 3 Gift Cards.
        giftCardService.createGiftCard(new GiftCard(null, "Disney", "50", "10000"));
        giftCardService.createGiftCard(new GiftCard(null, "Amazon", "50", "5000"));
        giftCardService.createGiftCard(new GiftCard(null, "Disney", "100", "20000"));
    }

    /**
     * Test combinations of multiple filter criteria for getAllGiftCards.
     */
    @Test
    public void testGetAllGiftCardsFilterByValueAndCompanyName() {
        List<GiftCard> result = giftCardService.getAllGiftCards("50", "Disney");

        assertEquals(1, result.size(), "Should find exactly 1 card matching both filters");
        assertEquals("Disney", result.get(0).getCompanyName());
        assertEquals("50", result.get(0).getValue());
    }

    /**
     * Test Company name is Case-Insensitive.
     */
    @Test
    public void testGetAllGiftCardsFilterIsCaseInsensitive() {
        List<GiftCard> result = giftCardService.getAllGiftCards("50", "disney");

        assertEquals(1, result.size(), "Filter should be case-insensitive");
    }
}