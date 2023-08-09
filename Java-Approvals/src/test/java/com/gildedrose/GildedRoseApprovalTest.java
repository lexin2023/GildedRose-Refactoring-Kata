package com.gildedrose;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

@UseReporter(DiffReporter.class)
public class GildedRoseApprovalTest {

	@Test
	public void ApprovalUpdateItemTest() {

        CombinationApprovals.verifyAllCombinations(
            this::doUpdateQuality,
            new String[] {"foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert",
                "Sulfuras, Hand of Ragnaros", "Elixir of the Mongoose", "Conjured Mana Cake"},
            new Integer[] {12, 10, 5, 0, -1},
            new Integer[] {50, 10, 5, 0, 1}
        );



	}

    private String doUpdateQuality(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        return gildedRose.items[0].toString();
    }

    @Test
    public void thirtyDays() {

        ByteArrayOutputStream fakeoutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(fakeoutput));
        System.setIn(new ByteArrayInputStream("a\n".getBytes()));

        Program.main();
        String output = fakeoutput.toString();

        Approvals.verify(output);
    }
}
