/**
 * Custom Form Validation
 */
function validateForm() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    
    // Check if the passwords match
    if (password !== confirmPassword) {
        alert('Passwords do not match!');
        return false; // Prevent form submission
    }

    // Add other necessary form validations here

    return true; // Allow form submission if valid
}
