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
    $('#msg').val('').focus();
    $('#src').val('');
    return false;
  });
});
