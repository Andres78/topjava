( function( $ ) {
    $(document).ready(function(){
        $('.langs').mouseenter(function(){
            $(this).children('div.langs_body').slideDown('normal');
        });
        $('.langs').mouseleave(function(){
            $(this).children('div.langs_body').slideUp('normal');
        });
    });
} )( jQuery );