package com.github.RecoverDev.j2docx.serialization.xml;

import com.github.RecoverDev.j2docx.block.Block;
import com.github.RecoverDev.j2docx.block.Listing;
import com.github.RecoverDev.j2docx.block.Paragraph;
import com.github.RecoverDev.j2docx.serialization.xml.numbering.NumberingModel;

public class ListingSerializer {

    public static void serialize(Listing listing, XmlStreamWriter writer) {

        NumberingModel numberingModel = NumberingModel.getInstance();
        Integer numId = numberingModel.getListings().get(listing).getId();
        Integer level = 0;

        listingSerialize(listing, numId, level, writer);

    }

    private static void listingSerialize(Listing listing, Integer numId, Integer level, XmlStreamWriter writer) {

        for (Block block : listing.getItems()) {
            if (block instanceof Paragraph paragraph) {
                NumberingPropertiesSerializer nPropertiesSerializer = new NumberingPropertiesSerializer(numId, level);
                ParagraphSerializerContext pContext = new ParagraphSerializerContext();
                pContext.getSerialize().add(nPropertiesSerializer);
                SerializerDispatcher.dispatche(paragraph, pContext, writer);
            }

            if (block instanceof Listing list) {
                listingSerialize(list, numId, level + 1, writer);
            }
        }
    }

}
