function addTableRow() {
    let technicalDetail = document.getElementById("technicalDetail").value
    let detailDescription = document.getElementById("detailDescription").value

    if (!technicalDetail || !detailDescription) {
        document.getElementById("input-table").innerHTML += `
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Parameters can't be empty!</strong>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            `;
        return
    }

    let table = document.getElementById("technical-details");
    let rowCount = table.rows.length;
    let row = table.insertRow(rowCount)
    let cell1 = row.insertCell(0);
    cell1.innerHTML = `<input class="form-control" readonly value="${technicalDetail}" name="technicalDetail[]"/>`;
    let cell2 = row.insertCell(1);
    cell2.innerHTML = `<input class="form-control" readOnly value="${detailDescription}" name="detailDescription[]"/>`;
    let cell3 = row.insertCell(2);
    cell3.innerHTML = `
        <div class="btn-group" role="group" aria-label="Basic mixed styles example">
            <button type="button" class="btn btn-primary" onclick="editTableRow(this)">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                    <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001m-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708z"/>
                </svg>
            </button>
            <button type="button" class="btn btn-danger" onclick="deleteTableRow(this)">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                    <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                </svg>
            </button>
        </div>`;
}

function deleteTableRow(deleteButton) {
    let tableRow = deleteButton.parentElement.parentElement.parentElement;
    tableRow.remove();
}

function editTableRow(editButton) {
    let tableRow = editButton.parentElement.parentElement.parentElement;
    let dataColumn0 = tableRow.childNodes[0];
    let dataColumn1 = tableRow.childNodes[1];
    let isReadonlyPresent0 = dataColumn0.firstChild.getAttribute('readonly');
    let isReadonlyPresent1 = dataColumn1.firstChild.getAttribute('readonly');
    isReadonlyPresent0 == null ? dataColumn0.firstChild.setAttribute('readonly', true) : dataColumn0.firstChild.removeAttribute('readonly');
    isReadonlyPresent1 == null ? dataColumn1.firstChild.setAttribute('readonly', true) : dataColumn1.firstChild.removeAttribute('readonly');
}