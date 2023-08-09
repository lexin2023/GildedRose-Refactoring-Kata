package com.gildedrose;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class GildedRose {
    Item[] items;

    private static final Map<String, Consumer<Item>> itemProcessors = new HashMap<>();

    static {
        itemProcessors.put("Aged Brie", GildedRose::doUpdateAgeBrieQuality);
        itemProcessors.put("Backstage passes to a TAFKAL80ETC concert", GildedRose::doUpdateBackStageQuality);
        itemProcessors.put("Sulfuras, Hand of Ragnaros", GildedRose::doUpdateSulfurasQuality);
        itemProcessors.put("Conjured Mana Cake", GildedRose::doUpdateConjuredQuality);
    }

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            doUpdateItemQualityV2(item);
        }
    }

    private void doUpdateItemQualityV2(Item item) {
        itemProcessors.getOrDefault(item.name, GildedRose::doUpdateDefault).accept(item);
    }

    private static void doUpdateDefault(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }

    private static void doUpdateSulfurasQuality(Item item) {
    }

    private static void doUpdateBackStageQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private static void doUpdateAgeBrieQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    private static void doUpdateConjuredQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 2;
        }

        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 2;
            }
        }
        item.quality = Math.max(0, item.quality);
    }

}
