package com.api.models;

public class OrderDO {

private Long id;
private Long petId;
private Long quantity;
private String shipDate;
private String status;
private Boolean complete;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getPetId() {
return petId;
}

public void setPetId(Long petId) {
this.petId = petId;
}

public Long getQuantity() {
return quantity;
}

public void setQuantity(Long quantity) {
this.quantity = quantity;
}

public String getShipDate() {
return shipDate;
}

public void setShipDate(String shipDate) {
this.shipDate = shipDate;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Boolean getComplete() {
return complete;
}

public void setComplete(Boolean complete) {
this.complete = complete;
}

@Override
public String toString() {
return "[\nid: "+ id +",\npetId: "+ petId +",\nquantity: "+ quantity +",\nshipDate: "+ shipDate 
		+",\nstatus: "+ status +",\ncomplete: "+ complete +"\n]";
}

}