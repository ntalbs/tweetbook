$(function () {
  $('#msg').focus();

  var update = function () {
    var msgCnt = $('#msg').val().length,
        srcCnt = $('#src').val().length,
        count = msgCnt + srcCnt + (srcCnt > 0 ? 1 : 0);
    $('#count').text(140 - count);
  };

  $('#msg').on('keyup', function (e) {
    update();
  });
  $('#src').on('keyup', function (e) {
    update();
  });

  $('form').submit(function (e) {
    $.post(
      "/addmesg",
      { mesg: $('#msg').val(), src: $('#src').val(), "tweet-immediately":  $('#tweet-immediately').val()}
    ).done(function (data) {
      console.log(data);
      $('#msg').val('').focus();
      $('#src').val('');
      $('#tweet-immediately').val('');
    }).fail(function (data) {
      console.error(data);
      console.error('fail');
    });
    return false;
  });
});
