# Read Me First
Carousell is a marketplace platform which allows users to ‘buy’ and ‘sell’ items.
This project is to implement the core functionality of a marketplace with the following entities:


* User 
    
    * Anyone who uses the marketplace Identified by username, which should be unique throughout the platform, but case insensitive.
Each user can create any number of listings having any category.

* Listing

    * A listing of an item put up for sale on the marketplace. Only registered users should be allowed to buy or sell items.
Listings should have the following fields:
    
        * Title
        * Description
        * Price
        * Username
        * Creation time
        * Each listing can be associated with only 1 user and 1 category.

* Category
    * Groupings of listings of the same "category". E.g. Electronics, Fashion etc
Category reads can be sorted on Price or creation time.


# Getting Started

* The project is implemented in Java with SpringBoot framework (2.0.1.RELEASE) and uses maven for dependency management.
* This project is a CommandLine Application and doesn't expose any RestAPI
        
        
        Below are the commands exposed by the application
        
        Built-In Commands
                clear: Clear the shell screen.
                exit, quit: Exit the shell.
                help: Display help about available commands.
                script: Read and execute commands from a file.
                stacktrace: Display the full stacktrace of the last error.
        
        Marketplace Command Interface
                CREATE_LISTING: Create a listing
                DELETE_LISTING: Delete a listing of the user
                GET_CATEGORY: Get listings for category
                GET_LISTING: Get listings
                GET_TOP_CATEGORY: Get category of the top listings
                REGISTER: Register a new user
                


* The application also contains in root directory the below executable files.
    
    * <b>build.sh</b> file to build the application, it will clean compile and also run the tests.
    * <b>run.sh</b> file to run the application
    * if the above helper files doesn't work run the below commands <b>chmod +x build.sh , chmod +x run.sh</b>
 
 * For the category of maximum number of listing we have used a cache to save on the query to database.
 This operation is expected to be a read heavy operation as it can be used on the home page etc. Please ensure suitable optimization for the same.
    
    * cache ttl can be configured in application.properties with property <b>cache-evict.ttl.categories</b>
    
    
 # Sample commands
 
     REGISTER user1
     CREATE_LISTING user1 'Phone model 8' 'Black color, brand new' 1000 'Electronics'
     GET_LISTING user1 100001
     CREATE_LISTING user1 'Black shoes' 'Training shoes' 100 'Sports'
     REGISTER user2
     REGISTER user2
     CREATE_LISTING user2 'T-shirt' 'White color' 20 'Sports'
     GET_LISTING user1 100003
     GET_CATEGORY user1 'Fashion' sort_time asc
     GET_CATEGORY user1 'Sports' sort_time dsc
     GET_CATEGORY user1 'Sports' sort_price dsc
     GET_TOP_CATEGORY user1
     DELETE_LISTING user1 100003
     DELETE_LISTING user2 100003
     GET_TOP_CATEGORY user2
     DELETE_LISTING user1 100002
     GET_TOP_CATEGORY user1
     GET_TOP_CATEGORY user3
     
 
 Console Output for the above commands
 
        >REGISTER user1
        Success
        >CREATE_LISTING user1 'Phone model 8' 'Black color, brand new' 1000 'Electronics'
        100001
        >GET_LISTING user1 100001
        Phone model 8|Black color, brand new|1000.0000|2020-03-06 18:01:18.979|Electronics|user1
        >CREATE_LISTING user1 'Black shoes' 'Training shoes' 100 'Sports'
        100002
        >REGISTER user2
        Success
        >REGISTER user2
        Error - user already existing
        >CREATE_LISTING user2 'T-shirt' 'White color' 20 'Sports'
        100003
        >GET_LISTING user1 100003
        T-shirt|White color|20.0000|2020-03-06 18:01:47.021|Sports|user2
        >GET_CATEGORY user1 'Fashion' sort_time asc
        Error - category not found
        >GET_CATEGORY user1 'Sports' sort_time dsc
        T-shirt|White color|20.0000|2020-03-06 18:01:47.021|Sports|user2
        Black shoes|Training shoes|100.0000|2020-03-06 18:01:29.024|Sports|user1
        >GET_CATEGORY user1 'Sports' sort_price dsc
        Black shoes|Training shoes|100.0000|2020-03-06 18:01:29.024|Sports|user1
        T-shirt|White color|20.0000|2020-03-06 18:01:47.021|Sports|user2
        >GET_TOP_CATEGORY user1
        Sports
        >DELETE_LISTING user1 100003
        Error - listing owner mismatch
        >DELETE_LISTING user2 100003
        Success
        >GET_TOP_CATEGORY user2
        Sports
        >DELETE_LISTING user1 100002
        Success
        >GET_TOP_CATEGORY user1
        Sports
        >GET_TOP_CATEGORY user3
        Error - unknown user
        >     