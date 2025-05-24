
    document.querySelectorAll(".button-edit-container>button").forEach((button) => {
    $.ajax({
        url: 'update-address',
        type: 'GET',
        data: {
            addressUserID: address.id
        },
        success: function(response){
            showSuccessMessage();
            console.log('thành công');
        },
    });
});
    document.querySelectorAll('.btn-plus').forEach((btn) => {
    btn.addEventListener('click', () => {
        window.location.href = 'add-address';
    });
});
    document.querySelectorAll('.address-component').forEach((address)=> {
    let buttonDelete = address.querySelector('.button-close-edit>.button-close-container>button');
    let buttonEdit = address.querySelector('.button-close-edit>.button-edit-container>button');
    buttonDelete.addEventListener('click', () => {
    $.ajax({
    url: 'delete-address',
    type: 'POST',
    data: {
    addressUserID: address.id
},
    success: function(response){
    console.log('thành công');
},
});
    address.styles.display = 'none';
});
    buttonEdit.addEventListener('click', () => {
    window.location.href = 'edit-address?addressUserID=' + address.id;
});
});
    document.querySelectorAll('.input-is-primary-address').forEach((input) => {
    input.addEventListener('change', () => {
        if(input.checked){
            $.ajax({
                url: 'set-primary-address',
                type: 'POST',
                data: {
                    addressUserID: input.id
                },
                success: function(response){
                    console.log('thành công');
                },
            })
        }
    });
});