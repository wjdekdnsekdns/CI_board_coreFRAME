
    const addButton = document.querySelector('.btn');

    addButton.addEventListener('click', function() {
    const newInput = document.createElement('input');
    newInput.setAttribute('type', 'file');
    newInput.setAttribute('class', 'form-control');
    newInput.setAttribute('id', 'formFile');

    const newLabel = document.createElement('label');
    newLabel.setAttribute('for', 'formFile');
    newLabel.setAttribute('class', 'form-label');

    const form = document.querySelector('.file_div');

    form.appendChild(newLabel);
    form.appendChild(newInput);});
