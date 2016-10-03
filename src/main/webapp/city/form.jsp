<!DOCTYPE html>
<html>
<head>
    <title>State</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <h1>State</h1>
        <form method="post">
            <div class="alert alert-success hide"></div>
            <div class="alert alert-danger hide"></div>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" class="form-control" maxlength="50" value="<%= request.getAttribute("name") == null ? "" : request.getAttribute("name") %>">
            </div>
            <input type="hidden" id="id" name="id" value="<%= request.getAttribute("id") %>">
            <button type="button" name="save" class="btn btn-success btn-save">Save</button>
            <% if (request.getAttribute("id") != null) { %>
            <button type="button" name="delete" class="btn btn-danger btn-delete">Delete</button>
            <% } %>
        </form>
    </div>
    <script>

        $(document).ready(function () {

            $.getJSON('/api/states', function (data) {
                var options = ['<option value="">[Select a state]</option>'];
                $.each(data, function (i, state) {
                    options.push('<option value="' + state.id + '">' + state.name + '</option>');
                });
                $('#state').html(options.join(''));
            });

            $('.btn-save').on('click', function () {
                $.ajax({
                    type: 'post',
                    contentType: 'application/json',
                    dataType: 'json',
                    url: '/api/city',
                    data: JSON.stringify({
                        id: $('#id').val(),
                        state: {
                            id: $('#state').val()
                        },
                        name: $('#name').val()
                    }),
                    success: function (data) {
                        $('.alert').addClass('hide');
                        if (data.status == 'success') {
                            $('.alert-success').removeClass('hide').html('Saved!');
                            $('input, select').val(null);
                        } else if (data.status == 'error') {
                            $('.alert-danger').removeClass('hide').html('Problem to save!');
                        }
                    }
                });
            });

            $('.btn-delete').on('click', function () {
                $.ajax({
                    type: 'delete',
                    contentType: 'application/json',
                    dataType: 'json',
                    url: '/api/city',
                    data: JSON.stringify({
                        id: $('#id').val(),
                        state: {
                            id: $('#state').val()
                        },
                        name: $('#name').val()
                    }),
                    success: function (data) {
                        $('.alert').addClass('hide');
                        if (data.status == 'success') {
                            $('.alert-success').removeClass('hide').html('Deleted!');
                            $('input, select').val(null);
                        } else if (data.status == 'error') {
                            $('.alert-danger').removeClass('hide').html('Problem to delete!');
                        }
                    }
                });
            })
        });

    </script>
</body>
</html>
