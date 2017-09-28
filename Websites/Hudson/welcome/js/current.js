let painting = 1;

$('#back').click(function () {
  painting > 1 ? painting = painting - 1 : painting = 21;
  $("#painting").attr("src", "../../images/current/" + painting + ".jpg");
})

$('#next').click(function () {
  painting < 21 ? painting = painting + 1 : painting = 1;
  $("#painting").attr("src", "../../images/current/" + painting + ".jpg");
})
