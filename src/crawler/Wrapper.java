package crawler;

//~--- non-JDK imports --------------------------------------------------------

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import productTypes.GraphicsCard;
import productTypes.Laptop;
import productTypes.MobilePhone;
import productTypes.Motherboard;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;

import java.math.BigDecimal;

import java.util.ArrayList;

/**
 * Created by d on 14/3/2015..
 */
public class Wrapper {

    /* PRODUCT TYPEs SUPPORTED */

    // array list for mobile phones
    ArrayList<MobilePhone> skrouztPhones = new ArrayList<>();

    // array list for GraphicsCards
    ArrayList<GraphicsCard> skrouztGraphicsCards = new ArrayList<>();

    // array list for Motherboards
    ArrayList<Motherboard> skrouztMotherboards = new ArrayList<>();

    // arraylist for Laptop
    ArrayList<Laptop> skrouztLaptops = new ArrayList<>();


    //get phone data
    protected  ArrayList<MobilePhone> getPhonesData() throws IOException{
        //search in all pages for phones
        for (int j = 1; j <= getPagenumberOfPhones(); j++) {
            Document mobilePhones = Jsoup.connect("http://www.skroutz.gr/c/40/kinhta-thlefwna.html?order_dir=asc&page="
                                        + j).userAgent("Mozilla").get();
            // get phone elements
            Elements phoneName   = mobilePhones.select("div[class=details]");
            Elements phonePrice  = mobilePhones.select("p[class=price]");
            Elements phoneSpecs  = mobilePhones.select("p[class=specs]");
            Elements phoneRating = mobilePhones.select("div[class=rating-wrapper] span");
            Elements phoneUrls   = mobilePhones.select("div[class=details] a ");

            // find the max
            double dMax = findMax(phoneName.size(), phonePrice.size(), phoneSpecs.size(), phoneRating.size(),
                                  phoneUrls.size());

            // cast double to int
            int intMax = (int) dMax;

            for (int i = 0; i < intMax; i++) {
                MobilePhone phones = new MobilePhone();

                // string for price
                String priceSt;
                priceSt = "0.0";
                if(i < phonePrice.size()){
                    // 1)replaceAll cut all the dots 2)replaceAll chenges the commas to dots and the last keeps only numbers and dots
                    priceSt = phonePrice.get(i).text().replaceAll("\\.", "").replaceAll(",", " .").replaceAll("[^0-9.]","");
                }    

                // parse the string to bigdecimal
                BigDecimal money = new BigDecimal(priceSt);

                //default value  = 0.0
                Double ratingDB = 0.0;
                //if rating exist get the rating from skrouzt site
                if(i < phoneRating.size())
                    ratingDB = new Double(phoneRating.get(i).text());

                // insert names in arralist
                phones.setName(phoneName.get(i).text());

                // insert price
                phones.setPrice(money);

                // insert specs
                phones.setSpecs(phoneSpecs.get(i).text());

                // insert rating
                phones.setRating(ratingDB);

                // insert urls
                phones.setUrl(phoneUrls.get(i).absUrl("href"));

                // add data to arraylist
                skrouztPhones.add(phones);
            }
        }

        return skrouztPhones;
    }

   
    //get GraphicsCars data
    protected  ArrayList<GraphicsCard> getGraphicsCardsData() throws IOException{
        //search in all pages  for graphics
        for (int j = 1; j <= getPageNumberOfGraphics(); j++) {
            Document graphics =
                Jsoup.connect(
                    "http://www.skroutz.gr/c/55/kartes-grafikwn-vga.html?keyphrase=graphics+card&order_dir=asc&page="
                    + j).userAgent("Mozilla").get();
            // get graphics elemets
            Elements graphicsName   = graphics.select("div[class=details]");
            Elements graphicsPrice  = graphics.select("p[class=price]");
            Elements graphicsSpecs  = graphics.select("p[class=specs]");
            Elements graphicsRating = graphics.select("div[class=rating-wrapper] span");
            Elements graphicsUrls   = graphics.select("div[class=details] a ");

            // find the max
            double dMax = findMax(graphicsName.size(), graphicsPrice.size(), graphicsSpecs.size(),
                                  graphicsRating.size(), graphicsUrls.size());
            // cast double to int
            int intMax = (int) dMax;
            //
            for (int i = 0; i < intMax; i++) {
                GraphicsCard graphicsCard = new GraphicsCard();

                // string for price
                String priceSt;

                 priceSt = "0.0";
                if(i < graphicsPrice.size()){
                    // 1)replaceAll cut all the dots 2)replaceAll chenges the commas to dots and the last keeps only numbers and dots
                    priceSt = graphicsPrice.get(i).text().replaceAll("\\.", "").replaceAll(",", " .").replaceAll("[^0-9.]","");
                }

                // parse the string to bigdecimal
                BigDecimal money = new BigDecimal(priceSt);

                // save rating double
               Double ratingDB = 0.0;
               if(i < graphicsRating.size())
                    ratingDB = new Double(graphicsRating.get(i).text());

                // insert names in arralist
                graphicsCard.setName(graphicsName.get(i).text());

                // insert price
                graphicsCard.setPrice(money);

                // insert specs
                graphicsCard.setSpecs(graphicsSpecs.get(i).text());

                // insert rating
                graphicsCard.setRating(ratingDB);

                // insert urls
                graphicsCard.setUrl(graphicsUrls.get(i).absUrl("href"));

                // add data to arraylist
                skrouztGraphicsCards.add(graphicsCard);
            }
        }

        return skrouztGraphicsCards;
    }

    //get motherboard data
    protected  ArrayList<Motherboard> getMotherboardsData() throws IOException{
        //search in all pages
        for (int j = 1; j <= getPageNumberOfMotherboards(); j++) {
            Document motherboard =
                Jsoup.connect(
                    "http://www.skroutz.gr/c/31/motherboards-mhtrikes.html?keyphrase=motherboard&order_dir=asc&page="
                    + j).userAgent("Mozila").get();

            // get all elements
            Elements motherboardName   = motherboard.select("div[class=details]");
            Elements motherboardPrice  = motherboard.select("p[class=price]");
            Elements motherboardSpecs  = motherboard.select("p[class=specs]");
            Elements motherboardRating = motherboard.select("div[class=rating-wrapper] span");
            Elements motherboardUrls   = motherboard.select("div[class=details] a ");

            // find the max of elements.size
            double dMax = findMax(motherboardName.size(), motherboardPrice.size(), motherboardSpecs.size(),
                                  motherboardRating.size(), motherboardUrls.size());

            // cast double to int
            int intMax = (int) dMax;

            for (int i = 0; i < intMax; i++) {
                Motherboard motherboards = new Motherboard();

                // string for price
                String priceSt;

                priceSt = "0.0";
                if(i < motherboardPrice.size()){
                    // 1)replaceAll cut all the dots 2)replaceAll chenges the commas to dots and the last keeps only numbers and dots
                    priceSt = motherboardPrice.get(i).text().replaceAll("\\.", "").replaceAll(",", " .").replaceAll("[^0-9.]","");
                }

                // parse the string to bigdecimal
                BigDecimal money = new BigDecimal(priceSt);

                // save rating double
                Double ratingDB = 0.0;
               if(i < motherboardRating.size())
                    ratingDB = new Double(motherboardRating.get(i).text());

                // insert names in arralist
                motherboards.setName(motherboardName.get(i).text());

                // insert price
                motherboards.setPrice(money);

                // insert specs
                motherboards.setSpecs(motherboardSpecs.get(i).text());

                // insert rating
                motherboards.setRating(ratingDB);

                // insert urls
                motherboards.setUrl(motherboardUrls.get(i).absUrl("href"));

                // add data to arraylist
                skrouztMotherboards.add(motherboards);
            }
        }

        return skrouztMotherboards;
    }



    
    //get motherboard data
    protected  ArrayList<Laptop> getLaptopsData() throws IOException{
        //search in all pages
        for (int j = 1; j <= getPageNumberOfLaptops(); j++) {
            Document laptop =
                Jsoup.connect("http://www.skroutz.gr/c/25/laptop.html?keyphrase=laptop&order_dir=asc&page="
                              + j).userAgent("Mozila").get();
            // get all elements
            Elements laptopName   = laptop.select("div[class=details]");
            Elements laptopPrice  = laptop.select("p[class=price]");
            Elements laptopSpecs  = laptop.select("p[class=specs]");
            Elements laptopRating = laptop.select("div[class=rating-wrapper] span");
            Elements laptopUrls   = laptop.select("div[class=details] a ");

            // find the max of elements.size
            double dMax = findMax(laptopName.size(), laptopPrice.size(), laptopSpecs.size(), laptopRating.size(),
                                  laptopUrls.size());

            // cast double to int
            int intMax = (int)Math.floor(dMax);

            for (int i = 0; i < intMax; i++) {
                Laptop laptops = new Laptop();

                // string for price
                String priceSt;

                priceSt = "0.0";
                if(i < laptopPrice.size()){
                    // 1)replaceAll cut all the dots 2)replaceAll chenges the commas to dots and the last keeps only numbers and dots
                    priceSt = laptopPrice.get(i).text().replaceAll("\\.", "").replaceAll(",", " .").replaceAll("[^0-9.]","");
                }
                
               // parse the string to bigdecimal
               BigDecimal money = new BigDecimal(priceSt);
               
                
                // save rating double
                Double ratingDB = 0.0;
               if(i < laptopRating.size())
                    ratingDB = new Double(laptopRating.get(i).text());
                // insert names in arralist
                laptops.setName(laptopName.get(i).text());
                
                // insert price
                laptops.setPrice(money);

                // insert specs
                laptops.setSpecs(laptopSpecs.get(i).text());

                // insert rating
                laptops.setRating(ratingDB);

                // insert urls
                laptops.setUrl(laptopUrls.get(i).absUrl("href"));

                // add data to arraylist
                skrouztLaptops.add(laptops);
            }
            /*
            for(int i = 0; i< laptopRating.size(); i++){
                Laptop laptopsr = new Laptop();
                Double ratingDB = new Double(laptopRating.get(i).text());
                laptopsr.setRating(ratingDB);
                skrouztLaptops.set(3, laptopsr);
            }
            */
            
        }

        return skrouztLaptops;
    }

   


    private int getPagenumberOfPhones() throws IOException {
        Document phone  = Jsoup.connect("http://www.skroutz.gr/c/40/kinhta-thlefwna.html").userAgent("Mozila").get();
        Elements number = phone.select("h1").select("span.count");

        // Replace all non digits with an empty character in a string
        String snumbers = removeNonDigits(number.text());

        // parse string to int
        int pgNo = Integer.parseInt(snumbers);

        // 18 is the number of products in every page + 1 for the last page
        pgNo = (pgNo / 18) + 1;

        return pgNo;
    }

    private static int getPageNumberOfGraphics() throws IOException {
        Document graphics =
            Jsoup.connect("http://www.skroutz.gr/c/55/kartes-grafikwn-vga.html").userAgent("Mozilla").get();

        // select element
        Elements number = graphics.select("h1").select("span.count");

        // Replace all non digits with an empty character in a string
        String snumbers = removeNonDigits(number.text());

        // parse string to int
        int pgNo = Integer.parseInt(snumbers);

        // 18 is the number of products in every page + 1 for the last page
        pgNo = (pgNo / 18) + 1;

        return pgNo;
    }

    // get the number of pages for motherboard
    private static int getPageNumberOfMotherboards() throws IOException {
        Document motherboard =
            Jsoup.connect("http://www.skroutz.gr/c/31/motherboards-mhtrikes.html").userAgent("Mozilla").get();

        // select element
        Elements number = motherboard.select("h1").select("span.count");

        // Replace all non digits with an empty character in a string
        String snumbers = removeNonDigits(number.text());

        // parse string to int
        int pgNo = Integer.parseInt(snumbers);

        // 18 is the number of products in every page + 1 for the last page
        pgNo = (pgNo / 18) + 1;

        return pgNo;
    }

    // get the number of pages for laptop
    private static int getPageNumberOfLaptops() throws IOException {
        Document laptop = Jsoup.connect("http://www.skroutz.gr/c/25/laptop.html").userAgent("Mozilla").get();

        // select element
        Elements number = laptop.select("h1").select("span.count");

        // Replace all non digits with an empty character in a string
        String snumbers = removeNonDigits(number.text());

        // parse string to int
        int pgNo = Integer.parseInt(snumbers);

        // 18 is the number of products in every page + 1 for the last page
        pgNo = (pgNo / 18) + 1;

        return pgNo;
    }
    
     // return the max from a number of values
    private static double findMax(double... vals) {

        // A constant holding the negative infinity of type double. It is equal to the value returned by Double.longBitsToDouble(0xfff0000000000000L).
        double max = Double.NEGATIVE_INFINITY;

        for (double d : vals) {
            if (d > max) {
                max = d;
            }
        }

        return max;
    }
    // Replace all non digits with an empty character in a string
    private static String removeNonDigits(final String str) {
        if ((str == null) || (str.length() == 0)) {
            return "";
        }

        // keep only numbers
        return str.replaceAll("\\D+", "");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
