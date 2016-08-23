package md.convertit.hydraulicsystem.domain;
/**
 * This class represents a Course object
 * @author Utilizator
 *
 */
public class Equipment {
	
	@Override
	public String toString() {
		return "Equipment [id=" + id + ", name=" + name + ", description=" + description + ", tag=" + tag + ", price="
				+ price + ", inStock=" + inStock + ", path_symbols=" + path_symbols + "]";
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getTag() {
		return tag;
	}
	public double getPrice() {
		return price;
	}
	public boolean isInStock() {
		return inStock;
	}
	public String getPath_symbols() {
		return path_symbols;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	public void setPath_symbols(String path_symbols) {
		this.path_symbols = path_symbols;
	}
	private Long id;
	private String name;
	private String description;
	private String tag;
	private double price;
	private boolean inStock;
	private String path_symbols;
	public Equipment() {
		super();
	}
	public Equipment(Long id, String name, String description, String tag, double price, boolean inStock,
			String path_symbols) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.tag = tag;
		this.price = price;
		this.inStock = inStock;
		this.path_symbols = path_symbols;
	}
	
	
}
