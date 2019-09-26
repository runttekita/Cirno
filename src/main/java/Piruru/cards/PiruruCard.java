package Piruru.cards;

import basemod.abstracts.CustomCard;

public abstract class PiruruCard extends CustomCard {

    public PiruruCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }
}
