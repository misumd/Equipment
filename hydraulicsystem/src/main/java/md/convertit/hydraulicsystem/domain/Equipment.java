package md.convertit.hydraulicsystem.domain;
/**
 * This class represents a Course object
 * @author Utilizator
 *
 */
public class Equipment {
	
	private Long id;
	private String name;
	private String description;
	private String tag;
	private double price;
	private boolean inStock;
	public Equipment() {
		super();
	}
	public Equipment(String name, String description, String tag, double price, boolean inStock) {
		super();
		this.name = name;
		this.description = description;
		this.tag = tag;
		this.price = price;
		this.inStock = inStock;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	@Override
	public String toString() {
		return "Equipment [id= " + id + ", name= " + name + ", description= " + description + ", tag= " + tag + ", price= "
				+ price + ", inStock= " + inStock + "]";
	}

}
