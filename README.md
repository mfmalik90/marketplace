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