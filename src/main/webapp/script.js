// Validate Login Form
function validateLoginForm() {
  let isValid = true;

  // Get the email and password input values
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  // Clear previous error messages
  document.getElementById("emailError").textContent = "";
  document.getElementById("passwordError").textContent = "";

  // Validate email (simple regex for validation)
  const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
  if (!emailPattern.test(email)) {
    document.getElementById("emailError").textContent = "Please enter a valid email address.";
    isValid = false;
  }

  // Validate password (minimum 6 characters)
  if (password.length < 6) {
    document.getElementById("passwordError").textContent = "Password must be at least 6 characters.";
    isValid = false;
  }

  return isValid;
}

// Validate Registration Form
function validateRegistrationForm() {
  let isValid = true;

  // Get the input values for registration
  const name = document.getElementById("name").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;

  // Clear previous error messages
  document.getElementById("nameError").textContent = "";
  document.getElementById("emailError").textContent = "";
  document.getElementById("passwordError").textContent = "";
  document.getElementById("confirmPasswordError").textContent = "";

  // Validate name (non-empty and letters only)
  const namePattern = /^[A-Za-z ]+$/;
  if (!namePattern.test(name)) {
    document.getElementById("nameError").textContent = "Name can only contain letters and spaces.";
    isValid = false;
  }

  // Validate email (simple regex for validation)
  const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
  if (!emailPattern.test(email)) {
    document.getElementById("emailError").textContent = "Please enter a valid email address.";
    isValid = false;
  }

  // Validate password (minimum 6 characters)
  if (password.length < 6) {
    document.getElementById("passwordError").textContent = "Password must be at least 6 characters.";
    isValid = false;
  }

  // Check password strength
  const passwordStrength = checkPasswordStrength(password);
  document.getElementById("passwordError").textContent += ` Password strength: ${passwordStrength}`;
  if (passwordStrength === 'Weak') {
    isValid = false;
  }

  // Confirm password validation
  if (password !== confirmPassword) {
    document.getElementById("confirmPasswordError").textContent = "Passwords do not match.";
    isValid = false;
  }

  return isValid;
}

// Password strength checker
function checkPasswordStrength(password) {
  const strengthRegex = {
    weak: /[a-z]/,
    medium: /[A-Z]/,
    strong: /[0-9]/,
  };

  if (password.length < 6) return 'Weak';
  if (strengthRegex.strong.test(password) && strengthRegex.medium.test(password)) return 'Strong';
  if (strengthRegex.medium.test(password)) return 'Medium';
  return 'Weak';
      }