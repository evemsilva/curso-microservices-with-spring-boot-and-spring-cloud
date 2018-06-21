package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    private Integer id;

    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;

    @Past
    private Date birthDate;

    public User() {
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
