<!DOCTYPE html>
<html>
    <head>
        <title>Cities</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <h1>Cities</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th width="50">ID</th>
                        <th>Name</th>
                        <th width="200">State</th>
                    </tr>
                </thead>
                <tbody class="table-data">

                </tbody>
            </table>
        </div>
        <script>

            $(document).ready(function () {
                $.getJSON('/api/cities', function (data) {
                    var items = [];
                    $.each(data, function (i, city) {
                        items.push(
                            '<tr>' +
                                '<td class="text-center">' + city.id + '</td>' +
                                '<td>' + city.name + '</td>' +
                                '<td>' + city.state.name + '</td>' +
                            '</tr>'
                        );
                    });
                    $('.table-data').html(items.join(''));
                });
            });

        </script>
    </body>
</html>
