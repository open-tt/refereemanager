<?php echo $this->element('Functions/table');	?>
<?php echo $this->element('Functions/assignments');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<?php
	if (empty($assignments)) {
?>
	<p><?php echo __('Es sind keine Schiedsrichtereinsätze gespeichert.'); ?></p>
<?php
	} else {

		$styles = array();
		$styles['normal'] = '';
		$styles['changed'] = sprintf('background-color: #%s; ', 'FFFFDD');

?>
	<table>
		<?php
			$columns = array();
				$columns[] = __('Datum');
				$columns[] = __('Uhrzeit');
				$columns[] = __('Spiel');
				$columns[] = __('Liga');
				$columns[] = __('Heimteam');
				$columns[] = __('Auswärtsteam');
				$columns[] = __('Schiedsrichter');
				$columns[] = __('OSR-Bericht');

				if ($isEditor) {
					$columns[] = __('Anmerkung');
				}

				$columns[] = __('Aktionen');
			$table = array('thead', 'tfoot');

			foreach ($table as $tabletag) {
				echo sprintf('<%s><tr>', $tabletag);
				foreach ($columns as $column) {
					echo sprintf('<th>%s</th>', $column);
				}
				echo sprintf('</tr></%s>', $tabletag);
			}
		?>
		<tbody>
			<?php
				foreach ($assignments as $assignment) {
					$curcol = 0;
			?>
					<tr>
						<?php
							echo(getTD($columns[$curcol++], $styles[$assignment['status']], $this->RefereeFormat->formatDate($assignment['start'], 'longdatereverse')));
							echo(getTD($columns[$curcol++], $styles[$assignment['status']], $this->RefereeFormat->formatDate($assignment['start'], 'time')));
							echo(getTD($columns[$curcol++], $styles[$assignment['status']], h($assignment['game_number'])));
							echo(getTD($columns[$curcol++], $styles[$assignment['status']], __($assignment['league'])));

							echo(getTD($columns[$curcol++], $styles[$assignment['status']], __('todo')));
							echo(getTD($columns[$curcol++], $styles[$assignment['status']], __('todo')));
							echo(getTD($columns[$curcol++], $styles[$assignment['status']], __('todo')));
							echo(getTD($columns[$curcol++], $styles[$assignment['status']], (getRefereeReport($assignment, $season) === null) ? '&cross;' : '&check;'));

							if ($isEditor) {
								echo(getTD($columns[$curcol++], $styles[$assignment['status']], (empty($assignment['remark'])) ? '' : h($assignment['remark'])));
							}

							echo(getTD(__('Aktionen'), $styles[$assignment['status']], $this->element('actions_table', array('id' => $assignment['id'])), 'actions'));
						?>
					</tr>
			<?php
				}
			?>
		</tbody>
	</table>

<?php
	}
?>

<?php pr($assignments); ?>

