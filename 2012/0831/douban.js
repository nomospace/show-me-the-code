$(function() {
  'use strict';
  var $books = $('#J_books'),
    tpl = $('#book_template').html();

  // http://www.douban.com/js/api.js?v=2
  // http://developers.douban.com/
  // http://book.douban.com/mine

  DOUBAN.apikey = '066fe3d230272c0e134fab769075842a';

  DOUBAN.getUserCollection({
    'uid': 'nomospace',
    'cat': 'book',
    'maxresults': '100',
    'callback': function(books) {
      function transform(object) {
        for (var i in object) {
          if (!i) continue;

          var key;
          if (i.indexOf('@') != -1) {
            key = i.split('@').join('');
          } else if (i.indexOf(':') != -1) {
            key = i.split(':').join('');
          } else if (i.indexOf('$') != -1) {
            key = i.split('$').join('');
          }

          if (key && i) {
            if (!object[key] && object[i]) object[key] = object[i];
          }

          if ($.isPlainObject(object[i]) || $.isArray(object[i])) {
            transform(object[i]);
          }

        }
      }

      transform(books);

      // console.log(books);
      var template = Handlebars.compile(tpl);

      Handlebars.registerHelper('image', function() {
        var result = '';
        $.each(this.link, function(i, r) {
          if (r.rel == 'image') {
            result = r.href;
            return false;
          }
        });
        return result;
      });

      Handlebars.registerHelper('href', function() {
        var result = '';
        $.each(this.link, function(i, r) {
          if (r.rel == 'alternate') {
            result = r.href;
            return false;
          }
        });
        return result;
      });

      Handlebars.registerHelper('publisher', function() {
        var result = '';
        $.each(this.dbattribute, function(i, r) {
          if (r.name == 'publisher') {
            result = r.t;
            return false;
          }
        });
        return result;
      });

      Handlebars.registerHelper('price', function() {
        var result = '';
        $.each(this.dbattribute, function(i, r) {
          if (r.name == 'price') {
            result = r.t;
            return false;
          }
        });
        return result;
      });

      Handlebars.registerHelper('pubdate', function() {
        var result = '';
        $.each(this.dbattribute, function(i, r) {
          if (r.name == 'pubdate') {
            result = r.t;
            return false;
          }
        });
        return result;
      });

      $books.html(template(books));
    }
  });
});
