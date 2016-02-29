$(document).ready(function () {

  function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
        //$('#j_idt85:blah').attr('src', e.target.result);
        $('#formId\\:blah').attr('src', e.target.result);
      }

      reader.readAsDataURL(input.files[0]);
    }
  }
  $("#formId\\:imgTmp_input").change(function () {
    readURL(this);
  });

  function readPicture(input, output)
  {
    if (input.files && input.files[0])
    {
      var reader = new FileReader();
      reader.onload = function (e)
      {
        output.attr('src', e.target.result);
      };
      reader.readAsDataURL(input.files[0]);
    }
  }

  $("[id='#{upload.clientId}']").change(
          function ()
          {
            readPicture(this, $("[id='#{image.clientId}']"));
          });
});


  