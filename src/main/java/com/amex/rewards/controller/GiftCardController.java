package com.amex.rewards.controller;


import com.amex.rewards.model.GiftCard;
import com.amex.rewards.service.GiftCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gift_cards")
public class GiftCardController {
    private final GiftCardService giftCardService;

    public GiftCardController(GiftCardService giftCardService){
        this.giftCardService = giftCardService;
    }

    // POST: Create a gift card.
    @PostMapping
    public ResponseEntity<GiftCard> createGiftCard(@RequestBody GiftCard giftCard){
        GiftCard newCard = giftCardService.createGiftCard(giftCard);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    // GET by ID. Returns 404 if the random UUID does not exist
    @GetMapping("/{id}")
    public ResponseEntity<GiftCard> getGiftCardById(@PathVariable String id){
        Optional<GiftCard> giftCardOptional = giftCardService.getGiftCardById(id);

        if (giftCardOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GiftCard card = giftCardOptional.get();
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    // DELETE by ID. Returns 204 No Content on success
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiftCardById(@PathVariable String id){
        boolean tryToDelete = giftCardService.deleteGiftCard(id);
        if (tryToDelete){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET /gift_cards - optional query params for filtering
    // URL: http://localhost:3000/gift_cards?value=50&companyName=Disney
    @GetMapping
    public ResponseEntity<List<GiftCard>> getAllGiftCards(
            @RequestParam(name = "value", required = false) String value,
            @RequestParam(name = "companyName", required = false) String companyName){
        List<GiftCard> cardList = giftCardService.getAllGiftCards(value,companyName);
        return new ResponseEntity<>(cardList,HttpStatus.OK);
    }
}
