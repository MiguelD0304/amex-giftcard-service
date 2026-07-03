package com.amex.rewards.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCard {
    private String id;

    @JsonProperty("company_name")
    private String companyName;

    private String value;

    @JsonProperty("points_cost")
    private String pointsCost;
}