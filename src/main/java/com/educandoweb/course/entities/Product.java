package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name, description;
	private Double price;
	private String imgUrl;

	@ManyToMany
	@JoinTable(name = "tb_product_category", // "tb_product_category" nome da tabela
			joinColumns = @JoinColumn(name = "product_id"), // (joinColumns) nome da coluna da chave extrangeira
			inverseJoinColumns = @JoinColumn(name = "category_id")) // (inverseJoinColumns) nome da chave extrangeira da
																	// outra entindade, ai colocamos novamente o
																	// @@JoinColumn no caso categoria
	private Set<Category> categories = new HashSet<>(); // Conjunto que garante que o mesmo produto nao vai ter mais de
														// uma categoria, onde é iniciado para nao começar nula.

	@OneToMany(mappedBy = "id.product") // Mesmo nome que ta na classe OrderItemPk
	private Set<OrderItem> items = new HashSet<>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@JsonIgnore
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();

		for (OrderItem orderItem : items) {
			set.add(orderItem.getOrder());
		}

		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
