$(function () {
  var update = function () {
    var msgCnt = $('#msg').val().length,
        srcCnt = $('#src').val().length,
        count = msgCnt + srcCnt + (srcCnt > 0 ? 1 : 0);
    $('#count').text(140 - count);
  };

  var clear = function () {
    $('#msg').val('').focus();
    $('#src').val('');
    $('#tweet-immediately').val('');
  };

  $('#msg, #src').on('keyup', function (e) { update(); });

  $('form').submit(function (e) {
    var quote = {
      msg: $('#msg').val(),
      src: $('#src').val(),
      "tweet-immediately":  $('#tweet-immediately').val()
    };

    var $listItem = $('<div class="alert alert-dismissible"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+quote.msg+'<br>'+quote.src +'</div>');

    $listItem.prependTo($('#list-container')).css({color: "gray"});

    $.post("/addmesg", quote).done(function (data) {
      console.log(data);
      clear();
      $listItem.addClass('alert-success');
    }).fail(function (data) {
      $listItem.addClass('alert-danger');
      console.error(data);
      console.error('fail');
    }).always(function () {
      update();
    });
    return false;
  });

  clear();
  $('#msg').focus();
});
