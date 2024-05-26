const imageUpload = document.getElementById('formFile');

imageUpload.addEventListener('change', function() {
    const file = imageUpload.files[0];
    const reader = new FileReader();
    reader.onload = function(e) {
        const imageDataUrl = e.target.result;
        const imagePreview = document.getElementById('imagePreview');
        imagePreview.src = imageDataUrl;
    };
    reader.readAsDataURL(file);
});