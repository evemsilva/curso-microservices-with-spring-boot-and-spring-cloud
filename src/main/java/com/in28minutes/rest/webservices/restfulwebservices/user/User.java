package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "All details about the user.")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have atleast 2 characters")
    @ApiModelProperty(notes = "Birth date should be in the past.")
    private String name;

    @Past
    @ApiModelProperty(notes = "Name should have atleast 2 characters.")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User(Integer id, String name, Date birthDate) {
	this.id = id;
	this.name = name;
	this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	User user = (User)o;
	return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

	return Objects.hash(id);
    }
}
