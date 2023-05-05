
package com.dev.myspace.model.request;

import lombok.NonNull;
import lombok.Data;

@Data
public class UserRequest {
	
	@NonNull
    private String userName;
    
    @NonNull
    private String password;

    @NonNull
    private String fullName;

    @NonNull
    private String dob;

}
