package com.employee.management.system;

import java.util.Date;

public class Test
{
	public static void main(String[] args)
	{

//		String str = "CHANGE_STATUS_TO_PENDING..";
//
//		System.out.println("String after toUpperCase:\t" + str.toUpperCase());
//		System.out.println("String after toLowerCase:\t" + str.toLowerCase());

		dateChecker();
	}

	public static void dateChecker()
	{
		Date start = new Date(System.currentTimeMillis());
		Date end = new Date(System.currentTimeMillis() + 1000 * 60 * 10);

		System.out.println("START:{}\t" + start);
		System.out.println("END:{}\t" + end);
	}
}

//		public String findByUserEmail(String email) {
//		    log.info("Fetching password for email: {}", email);
//		
//		    String sql = "SELECT password FROM users WHERE email = ?";
//		
//		    try {
//		        // Fetch only the password column
//		        String password = jdbcTemplate.queryForObject(sql, String.class, email);
//		        log.info("Password fetched successfully for email: {}", email);
//		        return password;
//		    } catch (EmptyResultDataAccessException e) {
//		        // No user found with the given email
//		        log.info("No user found with email: {}", email);
//		        return null;
//		    }
//		}

//========================

//	✅ Option 2: Create support users at application startup (BEST PRACTICE)
//	
//	Instead of SQL files, create support users programmatically when the app starts.
//	
//	@Component
//	public class DataInitializer {
//	
//	    @Autowired
//	    private UserRepository userRepository;
//	
//	    @Autowired
//	    private PasswordEncoder passwordEncoder;
//	
//	    @PostConstruct
//	    public void init() {
//	        if (!userRepository.existsByUsername("support")) {
//	            User u = new User();
//	            u.setUsername("support");
//	            u.setPassword(passwordEncoder.encode("support123"));
//	            u.setRole("ROLE_SUPPORT");
//	            userRepository.save(u);
//	        }
//	    }
//	}
//
