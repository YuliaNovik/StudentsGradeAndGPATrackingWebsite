package ca.sheridancollege.beans;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
		private Long userId;
		private String userName;
		private String encryptedPassword;
}
