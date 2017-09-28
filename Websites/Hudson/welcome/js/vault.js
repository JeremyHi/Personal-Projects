let painting = 1;

$('#back').click(function () {
  painting > 1 ? painting = painting - 1 : painting = 20;
  $("#painting").attr("src", "../../images/vault/" + painting + ".jpg");
})

$('#next').click(function () {
  painting < 20 ? painting = painting + 1 : painting = 1;
  $("#painting").attr("src", "../../images/vault/" + painting + ".jpg");
})
