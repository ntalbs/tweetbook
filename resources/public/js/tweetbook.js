$(function () {
  $('#msg').focus();

  var update = function () {
    var msgCnt = $('#msg').val().length,
        srcCnt = $('#src').val().length,
        count = msgCnt + srcCnt + (srcCnt > 0 ? 1 : 0);
    $('#count').text(140 - count);
  };

  $('#msg, #src').on('keyup', function (e) { update(); });

  $('form').submit(function (e) {
    var quote = {
      msg: $('#msg').val(),
      src: $('#src').val(),
      "tweet-immediately":  $('#tweet-immediately').val()
    };
    var $listItem = $('<li>'+quote.msg+'<br>'+quote.src +'</li>');

    $listItem.appendTo($('#list-container')).css({color: "gray"});

    $.post("/addmesg", quote).done(function (data) {
      console.log(data);
      $('#msg').val('').focus();
      $('#src').val('');
      $('#tweet-immediately').val('');
      $listItem.css({color: "green"});
    }).fail(function (data) {
      console.error(data);
      console.error('fail');
    });
    return false;
  });
});
