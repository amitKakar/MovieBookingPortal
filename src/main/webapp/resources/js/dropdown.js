		function DropDown(el) {
			
			this.dd = el;
			this.placeholder = this.dd.children('span');
			this.opts = this.dd.find('ul.dropdown > li');
			this.val = '';
			this.index = -1;
			this.initEvents();
		}

		DropDown.prototype = {
			initEvents : function() {
				var obj = this;

				obj.dd.on('click', function(event) {
					$(this).toggleClass('active');
					return false;
				});

				obj.opts.on('click', function() {
					var opt = $(this);
					obj.val = opt.text();
					obj.index = opt.index();
					obj.placeholder.text(obj.val);
				});
			},
			getValue : function() {
				return this.val;
			},
			getIndex : function() {
				return this.index;
			}
		}
		
		$(function() {
			
			var dd = new DropDown( $('#dd') );
			debugger;
			$(document).click(function() {
				// all dropdowns
				$('.wrapper-dropdown-3').removeClass('active');
			});

		});
		
		$(function() {

			var dd = new DropDown( $('#date-dropdown-div') );

			$(document).click(function() {
				// all dropdowns
				$('.wrapper-dropdown-3').removeClass('active');
			});

		});
		
		$(function() {

			var dd = new DropDown( $('#city-dropdown-div') );

			$(document).click(function() {
				$('.wrapper-dropdown-3').removeClass('active');
			});

		});
		
		$(function() {

			var dd = new DropDown( $('#theatre-dropdown-div') );

			$(document).click(function() {
				$('.wrapper-dropdown-3').removeClass('active');
			});

		});
		
		$(function() {

			var dd = new DropDown( $('#movie-dropdown-div') );

			$(document).click(function() {
				$('.wrapper-dropdown-3').removeClass('active');
			});

		});
		
		$(function() {

			var dd = new DropDown( $('#show-dropdown-div') );

			$(document).click(function() {
				$('.wrapper-dropdown-3').removeClass('active');
			});

		});
		
		/*$(function() {

			var dd = new DropDown( $('#seat-dropdown-div') );
			$(document).click(function() {
				$('.wrapper-dropdown-3').removeClass('active');
			});

		});*/
		
		$(document).ready(function(){
			$('#date-dropdown li a').click(function(){
				
				$("#date-dropdown li a").removeClass("selectedLi");
				
				$(this).addClass('selectedLi');
				
				var text = $(this).text();
				//alert(text);

			});
			
			$('#city-dropdown li a').click(function(){
				
				$("#city-dropdown li a").removeClass("selectedLi");
				
				$(this).addClass('selectedLi');
				
				var text = $(this).text();
				//alert("city name: "+text);
				
				loadTheatres(text);
			});
			
			$('#theatre-dropdown').delegate("li a","click", function(){
				
				//alert("ASasasasa");

				$("#theatre-dropdown li a").removeClass("selectedLi");
				
				$(this).addClass('selectedLi');
				
				var text = $(this).parent().text();
				var theatreId = $(this).parent().attr('id');
				//alert("theatre name:" + text);
				//alert("theatreId:"+theatreId); 
				
				loadMovies(theatreId);
			});
			
			$('#movie-dropdown').delegate("li a","click", function(){
				
				//alert("ASasasasa");
				$("#movie-dropdown li a").removeClass("selectedLi");
				
				$(this).addClass('selectedLi');
				
				var text = $(this).parent().text();
				var movieId = $(this).parent().attr('id');
				//alert(text);
				//alert(movieId);
				
				loadShows(movieId);
			});
			
			$('#show-dropdown').delegate("li a","click", function(){
				
				$("#show-dropdown li a").removeClass("selectedLi");
				
				$(this).addClass('selectedLi');
				
				var text = $(this).parent().text();
				var showId = $(this).parent().attr('id');
				//alert(text);
				//alert(showId);
				
				
				loadSeats(showId);
			});
			
		});