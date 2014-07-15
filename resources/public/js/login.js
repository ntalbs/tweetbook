$(function () {
  $('form').submit(function (e) {
    var $id = $('input[type="text"]'),
        $pw = $('input[type="password"]');
    var creds = {
      id: $id.val(),
      pw: $pw.val()
    };

    $.post("/login", creds).done(function (data) {
      window.location.replace(window.location.origin);
    }).fail(function (data) {
      $('input').css({"background-color": "#F5A9A9"});
    });
    return false;
  });
});
