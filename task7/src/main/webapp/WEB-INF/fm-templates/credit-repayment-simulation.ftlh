<div>

    <ul>
        <li>loan sum: ${credit.sum * (100 + credit.bankCommition) / 100} zł</li>
        <li>number of installments: ${credit.months}</li>
        <li>repaid sum: ${installment * credit.months} zł</li>
        <li>included interest: ${installment * credit.months - (credit.sum * (100 + credit.bankCommition) / 100)} zł</li>
        <li>RRSO: </li>
    </ul>
    <#list 1..credit.months as n>
        <tr>
            <td>${n}.</td>
            <td>${installment}</td>
            <td>${installment * credit.months - installment * n}</td>
        </tr>
        <br>
    </#list>
</div>
