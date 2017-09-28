let painting = 1;

$('#back').click(function () {
  painting > 1 ? painting = painting - 1 : painting = 18;
  $("#painting").attr("src", "../../images/words/" + painting + ".jpg");
})

$('#next').click(function () {
  painting < 18 ? painting = painting + 1 : painting = 1;
  $("#painting").attr("src", "../../images/words/" + painting + ".jpg");
})
