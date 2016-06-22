$(function() {
  $('a[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: target.offset().top - 250
        }, 1000);
        return false;
      }
    }
  });
});

(function($) {
  $(document).ready(function() {

    // hide .navbar first
    $("nav.navbar.navbar-default.navbar-fixed-top.blacked").hide();

    // fade in nav.navbar.navbar-default.navbar-fixed-top.blacked
    $(function() {
      $(window).scroll(function() {
        // set distance user needs to scroll before we fadeIn navbar
        if ($(this).scrollTop() > 100) {
          $('nav.navbar.navbar-default.navbar-fixed-top.blacked').fadeIn();
        } else {
          $('nav.navbar.navbar-default.navbar-fixed-top.blacked').fadeOut();
        }
      });


    });

  });
}(jQuery));
