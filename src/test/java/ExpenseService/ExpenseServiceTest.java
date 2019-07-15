package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {

  @Test
  void should_return_internal_expense_type_if_project_is_internal()
      throws UnexpectedProjectTypeException {
    // given
    ProjectType projectTypeExpected = ProjectType.INTERNAL;
    Project project = new Project(projectTypeExpected, "Project A");
    ExpenseType expenseTypeExpected = ExpenseType.INTERNAL_PROJECT_EXPENSE;
    // when
    ExpenseType expenseTypeActual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
    // then
    Assertions.assertEquals(expenseTypeExpected, expenseTypeActual);
  }
  @Test
  void should_return_expense_type_B_if_project_is_external_and_name_is_project_B()
      throws UnexpectedProjectTypeException {
    ProjectType projectTypeExpected = ProjectType.EXTERNAL;
    Project project = new Project(projectTypeExpected, "Project B");
    ExpenseType expenseTypeExpected = ExpenseType.EXPENSE_TYPE_B;
    // when
    ExpenseType expenseTypeActual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
    // then
    Assertions.assertEquals(expenseTypeExpected, expenseTypeActual);
  }
  @Test
  void should_return_expense_type_A_if_project_is_external_and_name_is_project_A()
      throws UnexpectedProjectTypeException {
    ProjectType projectTypeExpected = ProjectType.EXTERNAL;
    Project project = new Project(projectTypeExpected, "Project A");
    ExpenseType expenseTypeExpected = ExpenseType.EXPENSE_TYPE_A;
    // when
    ExpenseType expenseTypeActual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
    // then
    Assertions.assertEquals(expenseTypeExpected, expenseTypeActual);
  }
  @Test
  void should_return_other_expense_type_if_project_is_external_and_has_other_name()
      throws UnexpectedProjectTypeException {
    ProjectType projectTypeExpected = ProjectType.EXTERNAL;
    Project project = new Project(projectTypeExpected, "Project C");
    ExpenseType expenseTypeExpected = ExpenseType.OTHER_EXPENSE;
    // when
    ExpenseType expenseTypeActual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
    // then
    Assertions.assertEquals(expenseTypeExpected, expenseTypeActual);
  }
  @Test
  void should_throw_unexpected_project_exception_if_project_is_invalid() {
    ProjectType projectTypeExpected = ProjectType.UNEXPECTED_PROJECT_TYPE;
    Project project = new Project(projectTypeExpected, "Project C");
    // when
    try {
      ExpenseService.getExpenseCodeByProjectTypeAndName(project);
    } catch (UnexpectedProjectTypeException e) {
      // then
      Assertions.assertEquals("You enter invalid project type",e.getMessage());
    }
    // then
  }
}