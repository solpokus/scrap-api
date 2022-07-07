package id.scrap.model;

public class Item {
	
	private String nameOfProduct;
	private String description;
	private String imageLink;
	private String price;
	private String rating;
	private String nameOfStore;

	public String getNameOfProduct() {
		return nameOfProduct;
	}

	public void setnameOfProduct(String nameOfProduct) {
		this.nameOfProduct = nameOfProduct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getNameOfStore() {
		return nameOfStore;
	}

	public void setNameOfStore(String nameOfStore) {
		this.nameOfStore = nameOfStore;
	}

}