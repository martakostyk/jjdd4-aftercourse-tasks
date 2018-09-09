<form method="post" action="/task7/credit">
    <label>Credit sum [zł]</label>
    <input type="number" name="sum" min="0" step="0.01" placeholder="Credit sum" required>

    <label>Loan period [months]</label>
    <input type="number" name="months" min="0" step="1" placeholder="Loan period" required>

    <label>Bank margin [%]</label>
    <input type="number" name="bankMargin" min="0" step="0.01" placeholder="Bank margin" required>

    <label>Bank bankCommition [%]</label>
    <input type="number" name="bankCommition" min="0" step="0.01" placeholder="Bank Commition" required>

    <button type="submit" name="submit">Show credit repayment</button>
    <button type="reset" name="reset">Clear data</button>

</form>