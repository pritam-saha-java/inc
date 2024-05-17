
# InCallup Application 



Language stack : 
- Front end : htmx ,css,bootstrap , hyperscript, javascript 
- Backend : Java ,Kotlin, Thymeleaf (Template Engine) , Spring boot 

# Inc


API documentation is the [link](https://incallup.com/swagger-ui/index.html) format : http://{base-url}/swagger-ui/index.html


API documentation is the [link](https://localhost/incallup/swagger/ui) format : https://{base-url}/incallup/swagger/ui


translation : [translation link](https://incallup-com.translate.goog/dating/thane?_x_tr_sl=en&_x_tr_tl=hi&_x_tr_hl=en&_x_tr_pto=sc)


### branching strategy 
feature branch ----> testing branch ----> staging branch ----> main branch (production branch)


		



screen size for phone : 343 x 686

Done Changes :
 - sort the every dropdown data alphabetically (done)
 - signup link should be domain/register (done)
 - login link should be domain/login (done)
 - reduce the image size (done)
 - On homepage margins above the large text should be reduced (done)
 - On search page the title buttons should be moved and aligned to left (done)
 - align the homepage text as justified (done)
 - site text should be displayed from database (done)
 - load the bottom bars as preload and not through js (done)
 - category page mobile fix (done)
 - Footer Logo size should be fixed (done)
 - add watermark in image using java (test the logic in separate java file) (done)
 - fix search form (find out different test case scenarios) (done)
 - title & description length should be increased on category page(done)
 - post should take longer description (bug) (done)


UI Changes ---
- title page changes :
   - top text should be styled properly
 - Title page picture size should be fixed ()
 - There should be separate screens for mobile and desktop on title page.
 - improve seller panel UI
 - details page : age+ views text size should be increased
 - social media buttons size and shape should be fixed
 - distance between category page image and title decreased
 - title page should display disclaimer
 - form instructions should be updated




Functional Changes --
 - add incall outcall prices (in progress) (gets input needs to show on search page)
 - add validations for post form (need to check which validation are remaining) 
 - Create Post delete function for seller
 - admin panel 
 - increase the loading speed for search page
 - increase the limit of the image size
 - add compression in application for images (test out logic in separate java file)
 - bug in ads.html page ("post.location!=null?location.district: post.location !=null?post.location.district:'location'" (template: "ads" - line 71, col 57))


