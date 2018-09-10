<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body>

<div class="container" style="margin-top: 30px;">
    <div class="row">
        <div class="col-lg-9">
            <#setting locale="en_PL">
            <h4>Repayment simulation</h4>
            <br>
            <h5>Credit parameters</h5>
            <ul>
                <li>loan sum: ${(credit.sum * (100 + credit.bankCommition) / 100)?string.currency}</li>
                <li>number of installments: ${credit.months}</li>
                <li>repaid sum: ${(installment * credit.months)?string.currency}</li>
                <li>included
                    interest: ${(installment * credit.months - (credit.sum * (100 + credit.bankCommition) / 100))?string.currency}</li>
                <li>RRSO:</li>
            </ul>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-9">
            <h5>Repayment schedule</h5>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Month</th>
                    <th scope="col">Installment</th>
                    <th scope="col">Sum to be repaid</th>
                </tr>
                </thead>
                <tbody>
                    <#list 1..credit.months as n>
                        <tr>
                            <td>${n}.</td>
                            <td>${installment?string.currency}</td>
                            <td>${(installment * credit.months - installment * n)?string.currency}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>

        </div>
    </div>
</div>

</body>
</html>

