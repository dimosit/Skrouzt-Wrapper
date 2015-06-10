
package productTypes;

import java.math.BigDecimal;

/**
 *
 * @author d
 */
public class Product {
    protected String name;
    protected BigDecimal price;
    protected String specs;
    protected Double rating;
    protected String url;
   
    
    //cunstroctor
    public Product() {
        name = "";
        price=null;
        specs=null;
        rating=null;
        url="";
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
